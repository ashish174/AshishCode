package algo.practice.tree.binarytree.Heap;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Design Twitter Implement a simplified version of Twitter which allows users to post tweets,
 * follow/unfollow each other, and view the 10 most recent tweets within their own news feed.
 *
 * <p>Users and tweets are uniquely identified by their IDs (integers).
 *
 * <p>Implement the following methods:
 *
 * <p>Twitter() Initializes the twitter object. void postTweet(int userId, int tweetId) Publish a
 * new tweet with ID tweetId by the user userId. You may assume that each tweetId is unique.
 * List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's
 * news feed. Each item must be posted by users who the user is following or by the user themself.
 * Tweets IDs should be ordered from most recent to least recent. void follow(int followerId, int
 * followeeId) The user with ID followerId follows the user with ID followeeId. void unfollow(int
 * followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
 * Example 1:
 *
 * <p>Input: ["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], "getNewsFeed", [1],
 * "getNewsFeed", [2], "follow", [1, 2], "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2],
 * "getNewsFeed", [1]]
 *
 * <p>Output: [null, null, null, [10], [20], null, [20, 10], [20], null, [10]]
 *
 * <p>Explanation: Twitter twitter = new Twitter(); twitter.postTweet(1, 10); // User 1 posts a new
 * tweet with id = 10. twitter.postTweet(2, 20); // User 2 posts a new tweet with id = 20.
 * twitter.getNewsFeed(1); // User 1's news feed should only contain their own tweets -> [10].
 * twitter.getNewsFeed(2); // User 2's news feed should only contain their own tweets -> [20].
 * twitter.follow(1, 2); // User 1 follows user 2. twitter.getNewsFeed(1); // User 1's news feed
 * should contain both tweets from user 1 and user 2 -> [20, 10]. twitter.getNewsFeed(2); // User
 * 2's news feed should still only contain their own tweets -> [20]. twitter.unfollow(1, 2); // User
 * 1 unfollows user 2. twitter.getNewsFeed(1); // User 1's news feed should only contain their own
 * tweets -> [10].
 */
@Slf4j
public class Twitter {
  Map<Integer, List<TweetWithTimeStamp>> userFeed;
  Map<Integer, Set<Integer>> userFollows;
  Map<Integer, Queue<TweetWithTimeStamp>> userHomePageFeed;
  private static long globalTime = 0; // monotone logical clock

  public Twitter() {
    userFeed = new HashMap<>();
    userFollows = new HashMap<>();
    userHomePageFeed = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    // Add user to self as follow
    if (!userFollows.containsKey(userId)) {
      Set<Integer> follows = new HashSet<>();
      follows.add(userId);
      userFollows.put(userId, follows);
    }
    userFeed
        .computeIfAbsent(userId, k -> new LinkedList<>())
        .add(0, new TweetWithTimeStamp(userId, tweetId, ++globalTime));
    // Cant use epoch as processor is adding feeds within nanoseconds, while epoch resolution is
    // upto millisecond
  }

  public List<Integer> getNewsFeed(int userId) {
    Set<Integer> follows = userFollows.get(userId);
    Queue<TweetWithTimeStamp> tweetMinHeap =
        new PriorityQueue<>(Comparator.comparingLong(TweetWithTimeStamp::getEpochTime));
    for (int follow : follows) {
      long lastFeedTimeStamp = 0;
      if (userFeed.containsKey(follow)) {
        for (TweetWithTimeStamp tweet : userFeed.get(follow)) {
          // Keep adding tweet to feed if size < 10
          if (tweetMinHeap.size() < 10) {
            tweetMinHeap.add(tweet);
          } else { // if size is >= 10
            lastFeedTimeStamp = tweetMinHeap.peek().getEpochTime();
            if (lastFeedTimeStamp >= tweet.getEpochTime()) {
              // List is reverse sorted. So no need to check further.
              break;
            } else {
              tweetMinHeap.add(tweet);
            }
            if (tweetMinHeap.size() > 10) {
              tweetMinHeap.poll();
            }
          }
        }
      }
    }
    return tweetMinHeap.stream()
        .sorted(Comparator.comparingLong(TweetWithTimeStamp::getEpochTime).reversed())
        .map(TweetWithTimeStamp::getTweetId)
        .collect(Collectors.toList());
  }

  /**
   * Issue here is when user unfollows someone, there tweet remain in homefeed cache
   *
   * @param userId
   * @return
   */
  public List<Integer> getNewsFeedWithCache(int userId) {
    Set<Integer> follows = userFollows.get(userId);
    Queue<TweetWithTimeStamp> tweetMinHeap;
    long lastFeedTimeStamp = 0;

    if (!userHomePageFeed.containsKey(userId)) {
      lastFeedTimeStamp = 0;
      userHomePageFeed.put(
          userId, new PriorityQueue<>(Comparator.comparingLong(TweetWithTimeStamp::getEpochTime)));
      tweetMinHeap = userHomePageFeed.get(userId);
    } else {
      tweetMinHeap = userHomePageFeed.get(userId);
      lastFeedTimeStamp = tweetMinHeap.peek().epochTime;
    }
    // create home feed
    for (int follow : follows) {
      for (TweetWithTimeStamp tweet : userFeed.get(follow)) {
        if (lastFeedTimeStamp >= tweet.getEpochTime()) {
          break;
        }
        tweetMinHeap.add(tweet);
        if (tweetMinHeap.size() > 10) {
          tweetMinHeap.poll();
        }
      }
    }
    return tweetMinHeap.stream()
        .sorted(Comparator.comparingLong(TweetWithTimeStamp::getEpochTime).reversed())
        // .filter(tweetWithTimeStamp -> follows.contains(tweetWithTimeStamp.getUserId()))
        .map(TweetWithTimeStamp::getTweetId)
        .collect(Collectors.toList());
  }

  public void follow(int followerId, int followeeId) {
    userFollows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    if (userFollows.containsKey(followerId)) {
      userFollows.get(followerId).remove(followeeId);
    }
  }

  @Getter
  class TweetWithTimeStamp {
    int userId;
    int tweetId;
    long epochTime;

    public TweetWithTimeStamp(int userId, int tweetId, long epochTime) {
      this.userId = userId;
      this.tweetId = tweetId;
      this.epochTime = epochTime;
    }

    @Override
    public String toString() {
      return "TweetWithTimeStamp{"
          + "userId="
          + userId
          + ", tweetId="
          + tweetId
          + ", epochTime="
          + epochTime
          + '}';
    }
  }

  public static void main(String[] args) {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 10);
    twitter.postTweet(2, 20);
    log.info("user1 feed : {}", twitter.getNewsFeed(1));
    log.info("user2 feed : {}", twitter.getNewsFeed(2));
    twitter.follow(1, 2);
    log.info("user1 feed : {}", twitter.getNewsFeed(1));
    log.info("user2 feed : {}", twitter.getNewsFeed(2));
    // unfollow should remove follow feed
    twitter.unfollow(1, 2);
    log.info("user1 feed : {}", twitter.getNewsFeed(1));
    twitter.follow(2, 1);
    log.info("user2 feed : {}", twitter.getNewsFeed(2));
  }
}

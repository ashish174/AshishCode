package com.redmart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SampleMain {
  public static void main(String[] args){
    /*String s1 = "<p><span style=\"font-size: 10pt;color: #333333;\">Feature Benefits:<br/><br/>Improve SEO rankings by converting dynamic URLs into a Clean URL or Semantic URL, “static-looking” format in effort to improve search engine indexing<br/>Improve user experience by displaying your desired URL in the client address bar while delivering the payload from an alternate URL<br/>Achieve performance and offload by executing forward rewrite logic a the Edge of the Akamai Intelligent Platform, avoiding additional round trip requests to your data center<br/>Self-serviceable with an easy to use policy manager and included API<br/>Useful for:<br/><br/>Semantic URLs for improved user experience and SEO<br/>Rewriting URLs to exclude characters that could be hurting your search indexing<br/>Creating Clean URLs that your users can recognize and remember<br/>Delivering a whole site or application under a simplified or truncated URL shown in the address bar<br/>Serving m.foo.com application while displaying a www.foo.com domain<br/>Providing a user-friendly vanity-URL to browse site content that may be delivered from a URL with a dynamic structure<br/>Often overlooked when organizations design their web applications is the impact the URL structure and contents can have on search engine rankings and user experience. Should your URLs contain characters that have been recommended in SEO guides to be excluded like “=” or “&amp;” you could be negatively impacting your SEO. Also, if your URLs are a string of numbers and letters that mean nothing to the user you’re likely missing an opportunity to display a URL that both informs your visitor of the main idea or contents of the page but also to include critical keywords that will help a search engine index your page to be returned for relevant searches. You may also want to customize what URL is shown to the customer for branding or ease of use, in effort to make your URL easy to identify or memorable. With the Forward Rewrite Cloudlet, you’ll gain an easy to use tool that helps you to manage your rewrites and have them executed at the Edge of the Akamai Platform saving unnecessary long-haul requests back to your data center while offloading hits associated with URL rewrites from your web or application servers.</span><br/></p>";
    String s2 = "<p><span style=\"font-size: 10pt;color: #333333;\">Feature Benefits:<br/><br/>Improve SEO rankings by converting dynamic URLs into a Clean URL or Semantic URL, ?static-looking? format in effort to improve search engine indexing<br/>Improve user experience by displaying your desired URL in the client address bar while delivering the payload from an alternate URL<br/>Achieve performance and offload by executing forward rewrite logic a the Edge of the Akamai Intelligent Platform, avoiding additional round trip requests to your data center<br/>Self-serviceable with an easy to use policy manager and included API<br/>Useful for:<br/><br/>Semantic URLs for improved user experience and SEO<br/>Rewriting URLs to exclude characters that could be hurting your search indexing<br/>Creating Clean URLs that your users can recognize and remember<br/>Delivering a whole site or application under a simplified or truncated URL shown in the address bar<br/>Serving m.foo.com application while displaying a www.foo.com domain<br/>Providing a user-friendly vanity-URL to browse site content that may be delivered from a URL with a dynamic structure<br/>Often overlooked when organizations design their web applications is the impact the URL structure and contents can have on search engine rankings and user experience. Should your URLs contain characters that have been recommended in SEO guides to be excluded like ?=? or ?&amp;? you could be negatively impacting your SEO. Also, if your URLs are a string of numbers and letters that mean nothing to the user you?re likely missing an opportunity to display a URL that both informs your visitor of the main idea or contents of the page but also to include critical keywords that will help a search engine index your page to be returned for relevant searches. You may also want to customize what URL is shown to the customer for branding or ease of use, in effort to make your URL easy to identify or memorable. With the Forward Rewrite Cloudlet, you?ll gain an easy to use tool that helps you to manage your rewrites and have them executed at the Edge of the Akamai Platform saving unnecessary long-haul requests back to your data center while offloading hits associated with URL rewrites from your web or application servers.</span><br/></p>";
    s1 = s1.trim();
    s2 = s2.trim();
    if(s1.equals(s2))
      System.out.println("Pass");
    else
      System.out.println("Fail");*/
    String mp = "TRY";
    String pm = "Try Only";
    //OfferingType[] values = OfferingType.values();
    /*boolean flag;
    for(OfferingType offeringType: values){
     if(pm.equalsIgnoreCase(offeringType.getValue())){
       flag=true;
       break;
     }
    }*/

    List<String> lt = new ArrayList<String>();
    lt.add(mp);
    lt.add(pm);
    String s = lt.toString();
    System.out.println(s);
  }

  public enum OfferingType {

    TRY("Try Only"),
    BUY("Buy Only"),
    TRY_BUY("Try and Buy"),
    EXPRESS_INTEREST("EXPRESS_INTEREST"),
    FREE_PRODUCT("FREE_PRODUCT"),
    BETA("Beta"),
    BETA_CHANNEL("Beta Channels");
    String value;

    OfferingType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}

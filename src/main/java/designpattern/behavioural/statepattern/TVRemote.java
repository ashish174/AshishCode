package designpattern.behavioural.statepattern;

import org.apache.log4j.BasicConfigurator;

/**
 * Demonstrates use of the State Pattern with a TV remote.
 *
 * Try various sequences of commands to see how the internal state and
 * response changes!
 *
 * State Pattern allows an object's behavior to change with its internal state (here, the TV's state).
 * Context (TVContext) keeps a reference to the current State and delegates handling (commands) to it.
 * State Interface defines contract for all possible actions (here, they are interpreted as user commands).
 * Concrete States handle only valid requests and can change the state of the Context.
 * Demo: See how input commands affect the internal state and output.
 * Each state logs or restricts actions that are not allowed in the current state, making transitions explicit and easy to maintain.
 *
 */
public class TVRemote {
    public static void main(String[] args) {
        BasicConfigurator.configure(); // for Log4j demo output

        TVContext tv = new TVContext();

        tv.handleCommand("turnOn");    // TV OFF -> TV ON
        tv.handleCommand("mute");      // TV ON -> TV MUTED
        tv.handleCommand("unmute");    // TV MUTED -> TV ON
        tv.handleCommand("turnOff");   // TV ON -> TV OFF
        tv.handleCommand("mute");      // Invalid: TV is OFF
        tv.handleCommand("turnOn");    // TV OFF -> TV ON
        tv.handleCommand("turnOff");   // TV ON -> TV OFF
    }
}

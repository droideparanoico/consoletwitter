package com.droideparanoico.consoletwitter;

import com.droideparanoico.consoletwitter.command.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.droideparanoico.consoletwitter.domain.MessageTest;
import com.droideparanoico.consoletwitter.domain.UserTest;
import com.droideparanoico.consoletwitter.repository.UserRepositoryTest;
import com.droideparanoico.consoletwitter.ui.ConsoleTwitterPrinterTest;

/**
 * Unit tests for Console Twitter.
 */
@RunWith(Suite.class)
@SuiteClasses({ CommandInterpreterTest.class, FollowCommandTest.class, MessageCommandTest.class, ReadCommandTest.class,
		WallCommandTest.class, MessageTest.class, UserTest.class, ConsoleTwitterPrinterTest.class,
		UserRepositoryTest.class, InvalidCommandTest.class })
public class ConsoleTwitterTest {

}

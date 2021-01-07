package com.droideparanoico.consoletwitter.ui;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import com.droideparanoico.consoletwitter.domain.Message;

public class ConsoleTwitterPrinter implements Printer {
	private static final String MESSAGE_FORMAT_FOR_WALL = "%s - %s %s";
	private static final String MESSAGE_FORMAT_FOR_USER = "%s %s";
	private final PrintStream printStream;

	public ConsoleTwitterPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void printPostForWall(Message message) {
		printStream.printf((MESSAGE_FORMAT_FOR_WALL) + "%n", message.getUserName(), message.getMessage(),
				getTimeAgo(message.getPublishDate().getTime()));
	}

	@Override
	public void printPostForUser(Message message) {
		printStream.printf((MESSAGE_FORMAT_FOR_USER) + "%n", message.getMessage(),
				getTimeAgo(message.getPublishDate().getTime()));
	}

	@Override
	public void printMessage(String message) {
		printStream.println(message);
	}

	private static String getTimeAgo(long publishDate) {
		long now = System.currentTimeMillis();
		long diff = now - publishDate;
		long timeUnitDiff;
		String timeUnit;

		if (diff < TimeUnit.MINUTES.toMillis(1)) {
			timeUnitDiff = TimeUnit.MILLISECONDS.toSeconds(diff);
			timeUnit = "second";
		} else if (diff < TimeUnit.HOURS.toMillis(1)) {
			timeUnitDiff = TimeUnit.MILLISECONDS.toMinutes(diff);
			timeUnit = "minute";
		} else if (diff < TimeUnit.DAYS.toMillis(1)) {
			timeUnitDiff = TimeUnit.MILLISECONDS.toHours(diff);
			timeUnit = "hour";
		} else {
			timeUnitDiff = TimeUnit.MILLISECONDS.toDays(diff);
			timeUnit = "day";
		}

		if (timeUnitDiff > 1) {
			timeUnit = timeUnit + "s";
		}
		return String.format("(%d %s ago)", timeUnitDiff, timeUnit);
	}
}

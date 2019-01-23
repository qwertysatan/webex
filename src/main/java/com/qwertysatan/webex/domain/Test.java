package com.qwertysatan.webex.domain;

/**
 * Created by vladislav.uvarov on 21.01.2019.
 */
public class Test {

	private final long id;
	private final String content;

	public Test(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}

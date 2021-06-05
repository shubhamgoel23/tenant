package com.bms.tenants.config.mongo;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoClient;

@ChangeLog
public class DatabaseChangeLog {

	@ChangeSet(order = "006", id = "changeWithCustomBean", author = "mongock")
	public void changeWithCustomBean(MongoClient mongoClient) {
//		using mongo cient get all tenant database and execute the change set
		// You can use custom beans like your Spring Data repositories, as long as
		// they are interfaces
	}

}

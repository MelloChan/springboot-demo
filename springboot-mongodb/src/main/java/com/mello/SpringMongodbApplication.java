package com.mello;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@SpringBootApplication
public class SpringMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}
	@Bean
	public MongoClient client(){
		MongoClient client=new MongoClient(new ServerAddress("localhost",27017));
		return client;
	}
	@Bean
	public MongoDbFactory mongoDbFactory(){
		String database=new MongoClientURI("mongodb://localhost/test").getDatabase();
		return new SimpleMongoDbFactory(client(),database);
	}
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory){
		return new MongoTemplate(mongoDbFactory);
	}
}

package me.emmy.alley.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by Emmy
 * Project: Alley
 * Date: 21/05/2024 - 21:40
 */
@Getter
public class MongoService {

    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private final String uri;

    public MongoService(String uri) {
        this.uri = uri;
        this.createConnection();
    }

    private void createConnection() {
        try {
            ConnectionString connectionString = new ConnectionString(uri);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .retryWrites(true)
                    .build();

            this.mongoClient = MongoClients.create(settings);
            this.mongoDatabase = mongoClient.getDatabase(Objects.requireNonNull(connectionString.getDatabase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
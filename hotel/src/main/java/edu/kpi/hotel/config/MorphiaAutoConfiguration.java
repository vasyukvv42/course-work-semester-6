package edu.kpi.hotel.config;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.ValidationExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class MorphiaAutoConfiguration {

    private MongoClient mongoClient;

    private MongoTemplate mongoTemplate;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Autowired
    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Bean
    Morphia morphia() {
        var morphia = new Morphia();
        morphia.mapPackage("edu.kpi.hotel.model.entity");
        new ValidationExtension(morphia);
        return morphia;
    }

    @Bean
    public Datastore datastore(Morphia morphia) {
        Datastore dataStore = morphia.createDatastore(mongoClient, mongoTemplate.getDb().getName());
        dataStore.ensureCaps();
        dataStore.ensureIndexes();
        return dataStore;
    }

}

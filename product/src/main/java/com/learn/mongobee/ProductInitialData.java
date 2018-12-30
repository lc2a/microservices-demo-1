package com.learn.mongobee;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

@ChangeLog(order = "001")
public class ProductInitialData extends BaseMigration {


    @ChangeSet(id = "productCreation", order = "001", author = "Sunit Chatterjee")
    public void createProducts(MongoDatabase db) {

        MongoCollection<Document> productCollection = db.getCollection("product");

        List<Document> products = readInputJsonFiles("mongo/products.json");

        productCollection.insertMany(products);
    }
}

show dbs;
use microservices;

db.createCollection('Customers');
db.Customers.insertOne({"name": "spring"});
db.Customers.insertMany([{"name":"reactive"}, {"name":"microservices"}]);

db.Customers.find();
db.Customers.updateOne({"_id": ObjectId("62888ea103472136f3c0d92e")}, {"$set": {"name":"super spring"}});
db.Customers.deleteMany({"_id": ObjectId("62888ea103472136f3c0d92e")});

db.Customers.find({"name": "super spring"});
db.Customers.find({"name": /.*spring.*/i});
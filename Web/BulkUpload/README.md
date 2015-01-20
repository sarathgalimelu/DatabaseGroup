Bulk Uploader
=============

The bulkuploader tools go here.
1. Create a simple Mongo Collection(in this case articlesNew). The collection can be created as: `articlesNew = Mongo.Collection('articlesNew');`
2. In the shell script `test.sh`, input the path of the file which contains the articles to be uploaded.  
3. In terminal, enter into the corresponding directory using the `cd` command. 
4. Execute the shell script using the command: `sh test.sh`.
5. The articles will be uploaded into the collection articlesNew. 
6. To confirm upload, run command: `meteor mongo` 
7. Then use the command:`db.articlesNew.find().count()` and the exact count of articles into mongoDB will be shown as output. 


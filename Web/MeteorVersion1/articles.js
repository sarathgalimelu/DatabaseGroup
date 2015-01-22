

// Declare client article collection

Articles = new Meteor.Collection("articles");




 if (Meteor.isClient) {
    
    //Template.articlesTemplate.helpers({
  //articlesTemplate: function() {
   
                         
      //return Articles.find({'article.References.Ref.DOI':'10.1371/journal.pone.0000388'});
   //}
 //});
 Template.articlesTemplate.helpers({
    article: function() {
      return Articles.find({'article.References.Ref.DOI':'10.1371/journal.pone.0000388'}); //return Articles.find();
    }
  })
 
 }

 
if (Meteor.isServer) {
    
   
  Meteor.startup(function ()
                 {
    
      //if (Articles.find.count()==0)
      //{
      //  console.log(" no data in the database"); //code
      //}
  });
} 

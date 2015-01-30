Articles = new Meteor.Collection("articles");

if (Meteor.isClient) {
  // Bind articleTemplate to article collection
 
 Template.articleTemplate.helpers({
    article: function() {
      return Articles.find();
    }
  })
 
 }

if (Meteor.isServer) {
    
   
   Meteor.startup(function () {
    
    
  });
}

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
    
   var data1 = Assets.getText("fnins2010001881.json");
    
   // showing json file scontent in the server console
   console.log(data1);
   Meteor.startup(function () {
    
    
  });
}

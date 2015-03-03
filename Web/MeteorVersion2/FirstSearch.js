Articles = new Meteor.Collection("articles");
if (Meteor.isClient) {
  	Meteor.subscribe('allDocs');

	  Meteor.startup(function () {
	    Meteor.call('allDocs', function (err, count) {
	      Session.set('allDocs', count);
	    })
	  });

	  Template.search.helpers({
	      
	        
	    allDocs: function () {
	      return Session.get('allDocs');
	    }
	    
	  });

	  

	  
	  
	}


if (Meteor.isServer) {
  Meteor.startup(function () {
    // code to run on server at startup
	Meteor.methods({
      allDocs : function () {
        return Articles.find().count();
      }
    });
    //Articles._ensureIndex({'article.bibliography.article_doi': 1}); 
	Meteor.publish('allDocs', function () {
      return [
        Articles.find({}, { limit: 10 })//.sort({'article.bibliography.article_doi': 1})
        
      ];
    });
  });
}

// Search Index for the articles search
EasySearch.createSearchIndex('text', {
  'collection': Articles, // instanceof Meteor.Collection
  'field': ['article.bibliography.article_doi', 'article.bibliography.article_title','article.bibliography.article_pub_year','article.bibliography.abstract', 'article.sections.section.Section_title.content', 'article.sections.section.Section_Details.content'],
  'limit': 10,
  'use' : 'mongo-db',
  'convertNumbers': true,
   'props': {
    'filteredCategory': 'All',
    'sortBy': ['article.sections.section.Section_Details.content','article.bibliography.article_doi']
  },
  'sort': function() {
    if (this.props.sortBy === 'article.sections.section.Section_Details.content') {
      return { 'article.sections.section.Section_Details.content': 1};
    }  
    
    else if(this.props.sortBy === 'article.bibliography.article_doi' ) {
      return { 'article.bibliography.article_doi': 1};
    }
    },
  
  'query': function(searchString, opts) {
    // Default query that will be used for the mongo-db selector
    var query = EasySearch.getSearcher(this.use).defaultQuery(this, searchString);

    // filter for categories if set
   

    return query;
  }
});

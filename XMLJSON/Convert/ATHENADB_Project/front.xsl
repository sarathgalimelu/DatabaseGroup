<?xml version="1.0" encoding="UTF-8"?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) at Thu Mar 20 14:29:22 EDT 2014 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" indent="yes"/>
<xsl:strip-space elements="Section_title Subsection_title abstract"/>
	
	<xsl:template match="/article">
		<xsl:element name="article">	
			<xsl:element name="bibliography">
					
						<xsl:element name="article_doi">
							<xsl:value-of select="//article-id[@pub-id-type='doi'] "/>					
						</xsl:element>									 	
						<xsl:element name="article_publisher_id">
							 <xsl:value-of select="//article-id[@pub-id-type='publisher-id'] "/> 
						</xsl:element>	
						<xsl:element name="article_title">
							<xsl:value-of select="front/article-meta/title-group/article-title"/>
						</xsl:element>
						<xsl:element name="abstract">
							<xsl:value-of select="front/article-meta/abstract"/>
						</xsl:element>
						<xsl:element name="article_pub_year">
							<xsl:value-of select="front/article-meta/pub-date[ @pub-type='epub']/year"/>
						</xsl:element>
						<xsl:element name="volume">
							<xsl:value-of select="front/article-meta/volume"/>
						</xsl:element>
						<xsl:element name="journal_id">
							<xsl:value-of select="//journal-id[@journal-id-type='publisher-id'] "/>
						</xsl:element>
						<xsl:element name="journal_title">
							<xsl:value-of select="front/journal-meta/journal-title"/>
						</xsl:element>
						<xsl:element name="journal_title_PLOS">
							<xsl:value-of select="front/journal-meta/journal-title-group/journal-title"/>
						</xsl:element>

						<xsl:element name="publisher_name">
							<xsl:value-of select="front/journal-meta/publisher/publisher-name"/>
						</xsl:element>
						<xsl:element name="kwd_group">
										
							<xsl:for-each select="//kwd">
								<xsl:element name="kwd">											
									<xsl:value-of select="."/>		
								</xsl:element>
							</xsl:for-each >
						</xsl:element>
											
						<xsl:for-each select="//contrib [@contrib-type='author']">
							<xsl:element name="author">
								<xsl:element name="surname">
									<xsl:value-of select="name/surname"/>
								</xsl:element>	
										
								<xsl:element name="given_name">
									<xsl:value-of select="name/given-names"/>	
								</xsl:element>	
							</xsl:element>
									
						</xsl:for-each >					
						</xsl:element>
						<xsl:element name="sections">
						<xsl:element name="free_text">
							<xsl:for-each select="//body/p">										
								<xsl:value-of select="."/>
							</xsl:for-each >	
						</xsl:element>
		                                
						<xsl:for-each select="body/sec">
                            <xsl:element name="section"> 
							<xsl:element name="Section_title">			
								<xsl:value-of select="title"/>
								 <xsl:choose>
								 	<xsl:when test="contains(title,'Introduction')">
	 			 						 <xsl:element name="Soft_title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'introduction')">
	 			 						 <xsl:element name="Soft_title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'INTRODUCTION')">
	 			 						 <xsl:element name="Soft_title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Method')">
	 			 						 <xsl:element name="Soft_title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'method')">
	 			 						 <xsl:element name="Soft_title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'METHOD')">
	 			 						 <xsl:element name="Soft_title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'conclusion')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Conclusion')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'CONCLUSION')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'discussion')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'Discussion')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					   <xsl:when test="contains(title,'DISCUSSION')">
	 			 						 <xsl:element name="Soft_title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'result')">
	 			 						 <xsl:element name="Soft_title">Results</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Result')">
	 			 						 <xsl:element name="Soft_title">Results</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'RESULT')">
	 			 						 <xsl:element name="Soft_title">Results</xsl:element>
								     </xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="title"/>
									</xsl:otherwise>	
	 							  </xsl:choose>
								 </xsl:element>
								<xsl:element name="Section_Details">
									<xsl:for-each select="p">										
										<xsl:value-of select="."/>
									</xsl:for-each >	
								
<xsl:element name="Subsections">														
<xsl:for-each select="sec">	
<xsl:element name="Subsection">	
<xsl:element name="Subsection_title">									
										<xsl:value-of select="title"/>
</xsl:element>											
										<xsl:element name="Subsection_Details">
											<xsl:for-each select="p">										
										<xsl:value-of select="."/>
</xsl:for-each >	
										
	<xsl:element name="subtopics">
	<xsl:for-each select="sec">	
		<xsl:element name="subtopic">
	<xsl:element name="subtopic_title">									
		<xsl:value-of select="title"/>
	</xsl:element>											
	<xsl:element name="Subtopic_Details">
		<xsl:for-each select="p">										
			<xsl:value-of select="."/>
		</xsl:for-each >	
	</xsl:element>
</xsl:element>							
</xsl:for-each >
	</xsl:element>
						</xsl:element>	
</xsl:element>	
</xsl:for-each >				

</xsl:element>
								</xsl:element>
                            </xsl:element>
						</xsl:for-each>
						</xsl:element>
		
				<xsl:element name="References">
						
							<xsl:for-each select="//back/ref-list/ref/mixed-citation">
								<xsl:element name="Ref">
								<xsl:element name="Author">	
									<xsl:for-each select="name">							
										<xsl:element name="Surname">									
											<xsl:value-of select="surname"/>
										</xsl:element>
										<xsl:element name="Given_name">									
											<xsl:value-of select="given-names"/>
										</xsl:element>								
									</xsl:for-each >	
								</xsl:element>
								<xsl:element name="Year">									
									<xsl:value-of select="year"/>
								</xsl:element>
								<xsl:element name="Article_title">									
									<xsl:value-of select="article-title"/>
								</xsl:element>
								<xsl:element name="Source">									
									<xsl:value-of select="source"/>
								</xsl:element>
								<xsl:element name="Volume">									
									<xsl:value-of select="volume"/>
								</xsl:element>
								<xsl:element name="Fpage">									
									<xsl:value-of select="fpage"/>
								</xsl:element>
<xsl:element name="Lpage">									
<xsl:value-of select="lpage"/>
</xsl:element>
<xsl:element name="DOI">									
<xsl:value-of select="//pub-id[@pub-id-type='doi'] "/>
</xsl:element>
								
								</xsl:element>									
							</xsl:for-each >	
							
							<xsl:for-each select="//back/ref-list/ref/citation">
								<xsl:element name="Ref">
								<xsl:element name="Author">	
									<xsl:for-each select="name">							
										<xsl:element name="Surname">									
											<xsl:value-of select="surname"/>
										</xsl:element>
										<xsl:element name="Given_name">									
											<xsl:value-of select="given-names"/>
										</xsl:element>								
									</xsl:for-each >	
								</xsl:element>
								<xsl:element name="Year">									
									<xsl:value-of select="year"/>
								</xsl:element>
								<xsl:element name="Article_title">									
									<xsl:value-of select="article-title"/>
								</xsl:element>
								<xsl:element name="Source">									
									<xsl:value-of select="source"/>
								</xsl:element>
								<xsl:element name="Volume">									
									<xsl:value-of select="volume"/>
								</xsl:element>
								<xsl:element name="Fpage">									
									<xsl:value-of select="fpage"/>
								</xsl:element>
								<xsl:element name="Lpage">									
									<xsl:value-of select="lpage"/>
								</xsl:element>
<xsl:element name="DOI">									
<xsl:value-of select="//pub-id[@pub-id-type='doi'] "/>
</xsl:element>
								
								</xsl:element>									
							</xsl:for-each >		
							
						</xsl:element>

										
				
			</xsl:element>
		
	</xsl:template>
	

</xsl:stylesheet>

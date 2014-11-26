<?xml version="1.0" encoding="UTF-8" ?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) at Thu Mar 20 14:29:22 EDT 2014 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" indent="yes"/>

<xsl:strip-space elements="Section-title Subsection-title abstract"/>
	
	<xsl:template match="/article">
		<xsl:element name="article">	
			<xsl:element name="bibliography">
					
						<xsl:element name="article-doi">	
							<xsl:value-of select="//article-id[@pub-id-type='doi'] "/>					
						</xsl:element>									 	
						<xsl:element name="article-publisher-id">
							 <xsl:value-of select="//article-id[@pub-id-type='publisher-id'] "/> 
						</xsl:element>	
						<xsl:element name="article-title">
							<xsl:value-of select="front/article-meta/title-group/article-title"/>
						</xsl:element>
						<xsl:element name="abstract">
							<xsl:value-of select="front/article-meta/abstract"/>
						</xsl:element>
						<xsl:element name="article-pub-year">
							<xsl:value-of select="front/article-meta/pub-date[ @pub-type='epub']/year"/>
						</xsl:element>
						<xsl:element name="volume">
							<xsl:value-of select="front/article-meta/volume"/>
						</xsl:element>
						<xsl:element name="journal-id">
							<xsl:value-of select="//journal-id[@journal-id-type='publisher-id'] "/>
						</xsl:element>
						<xsl:element name="journal-title">
							<xsl:value-of select="front/journal-meta/journal-title"/>
						</xsl:element>
						<xsl:element name="journal-title-PLOS">
							<xsl:value-of select="front/journal-meta/journal-title-group/journal-title"/>
						</xsl:element>

						<xsl:element name="publisher-name">
							<xsl:value-of select="front/journal-meta/publisher/publisher-name"/>
						</xsl:element>
						
                                                <xsl:element name="article-categories">
										
							<xsl:for-each select="//article-categories/subj-group[@subj-group-type='heading']">
								<xsl:element name="type">											
									<xsl:value-of select="."/>		
								</xsl:element>
							</xsl:for-each >
							<xsl:for-each select="//article-categories/subj-group[@subj-group-type='Discipline']/subject">
								<xsl:element name="Discipline">											
									<xsl:value-of select="."/>		
								</xsl:element>
							</xsl:for-each >
						</xsl:element>
											
						<xsl:for-each select="//contrib [@contrib-type='author']">
							<xsl:element name="author">
								<xsl:element name="surname">
									<xsl:value-of select="name/surname"/>
								</xsl:element>	
										
								<xsl:element name="given-name">
									<xsl:value-of select="name/given-names"/>	
								</xsl:element>	
							</xsl:element>
									
						</xsl:for-each >					
						</xsl:element>
						<xsl:element name="sections">
						<xsl:element name="free-text">
							<xsl:for-each select="//body/p">										
								<xsl:value-of select="."/>
							</xsl:for-each >	
						</xsl:element>
		
						<xsl:for-each select="body/sec">
							<xsl:element name="Section-title">			
								<xsl:value-of select="title"/>
								 <xsl:choose>
								 	<xsl:when test="contains(title,'Introduction')">
	 			 						 <xsl:element name="Soft-title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'introduction')">
	 			 						 <xsl:element name="Soft-title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'INTRODUCTION')">
	 			 						 <xsl:element name="Soft-title">Introduction</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Method')">
	 			 						 <xsl:element name="Soft-title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'method')">
	 			 						 <xsl:element name="Soft-title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'METHOD')">
	 			 						 <xsl:element name="Soft-title">Methods</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'conclusion')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Conclusion')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'CONCLUSION')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'discussion')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'Discussion')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					   <xsl:when test="contains(title,'DISCUSSION')">
	 			 						 <xsl:element name="Soft-title">Discussions and Conclusions</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'result')">
	 			 						 <xsl:element name="Soft-title">Results</xsl:element>
	 			 					 </xsl:when>
	 			 					 <xsl:when test="contains(title,'Result')">
	 			 						 <xsl:element name="Soft-title">Results</xsl:element>
	 			 					 </xsl:when>
	 			 					  <xsl:when test="contains(title,'RESULT')">
	 			 						 <xsl:element name="Soft-title">Results</xsl:element>
	 			 					 </xsl:when>	
	 							  </xsl:choose>
								 </xsl:element>
								<xsl:element name="Details">
									<xsl:for-each select="p">										
										<xsl:value-of select="."/>
									</xsl:for-each >	
								</xsl:element>
														
<xsl:for-each select="sec">	
<xsl:element name="Subsection-title">									
										<xsl:value-of select="title"/>
</xsl:element>											
										<xsl:element name="Subsection-Details">
											<xsl:for-each select="p">										
										<xsl:value-of select="."/>
</xsl:for-each >	
</xsl:element>

							
</xsl:for-each >	
	
</xsl:for-each >				
		
</xsl:element>
				<xsl:element name="References">
						
							<xsl:for-each select="//back/ref-list/ref/element-citation">
								<xsl:element name="Ref">
								<xsl:element name="Author">	
									<xsl:for-each select="name">							
										<xsl:element name="Surname">									
											<xsl:value-of select="surname"/>
										</xsl:element>
										<xsl:element name="Given-name">									
											<xsl:value-of select="given-names"/>
										</xsl:element>								
									</xsl:for-each >	
								</xsl:element>
								<xsl:element name="Year">									
									<xsl:value-of select="year"/>
								</xsl:element>
								<xsl:element name="Article-title">									
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

								
								</xsl:element>									
							</xsl:for-each >	
							
									
							
						</xsl:element>

										
				
			</xsl:element>
		
	</xsl:template>
	


</xsl:stylesheet>

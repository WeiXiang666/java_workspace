<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0"
    xmlns:xalan="http://xml.apache.org/xslt">
   
    <xsl:template match="students">
        <TABLE BORDER="1">
   	
            <xsl:for-each select="student" >
                <tr>
                    <td>
                        <xsl:value-of select="id"/>
                    </td>
                    <td>
                        <xsl:value-of select="name"/>
                    </td>
                    <td>
                        <xsl:value-of select="email"/>
                    </td>
                </tr>
            </xsl:for-each>
        </TABLE>
    </xsl:template>
    
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Delete</description>
   <name>Delete User</name>
   <tag></tag>
   <elementGuidId>45a924fb-3dab-4fd4-a9f5-9ec490a8cadd</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;query\&quot;: \&quot;select * from nexcare.user_nexcare where id \u003d 3090;\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>20f31190-5345-4d12-b317-21625ed5ef33</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODc3NjgxMjcsIlVzZXJuYW1lIjoibmV4Y2FyZSIsIkVtYWlsIjoibmV4Y2FyZUBuZXhzb2Z0LmNvLmlkIiwiR3JvdXAiOiJhdXRvbWF0aW9uIn0.3thYuSNgdS95NzbJ5QKFfjveYc8kuLmwaSKtW5OLwcI</value>
      <webElementGuid>f576e308-8419-43f5-8a0b-6785e188c452</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.3.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare/queries</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

KeywordUtil.logInfo(&quot;Response : &quot; + response.toString())
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>

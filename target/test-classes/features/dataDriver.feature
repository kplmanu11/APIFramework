Feature: Validating the Datadriven Test

Scenario Outline: Verify if the data driven functionlity is working fine
	Given Add the data through "<name>" "<language>" "<address>"
	When user calls "API" with the post http request
	Then the API call got success with the status code 200
	And "status"  in response body is "OK"
	And "scope" in response body is "APP"
	
Examples:
		|name|	language	|	address|
		|AAhouse	|	English|	World cross center|
		|BBhouse	|Spanish | 	Sea Cross center	| 
		
This is provided as a personal example, not a production ready script nor a script that can be maintained in the long term

Use Maven to package, and then create an AWS Lambda function. Upload the code to the Lambda function, give it a test event with parameter like:

"myStackName"

And watch it successfully describe a stack :)

It is recommended to change the S3 bucket in upload.sh to yours, as well as change the Lambda function name in update-function.sh and invoke.sh to yours. These bash scripts are merely helpers for quick iteration, rather than typing out the full AWS CLI commad each time.

invoke.sh at the moment will always fail as no parameters are given; I recommend testing from the AWS Console and create Lambda Test event there.


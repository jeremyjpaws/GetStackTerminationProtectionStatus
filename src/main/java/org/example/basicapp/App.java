package org.example.basicapp;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.model.DescribeStackResourcesRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksRequest;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.amazonaws.services.cloudformation.model.StackStatus;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.*;
import com.amazonaws.regions.Regions;
import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public String myHandler(String stackName, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Beginning...");
        final AmazonCloudFormation cfn = AmazonCloudFormationClientBuilder.standard()
          .withRegion(Regions.US_EAST_1)
          .build();

        Stack myStack = null;

        logger.log("Describing stacks...");
        List<Stack> stacks = cfn.describeStacks(new DescribeStacksRequest()
          .withStackName(stackName))
          .getStacks();

        myStack = stacks.get(0);


        logger.log("Getting stack attributes...");
        String description = myStack.getDescription();
        Boolean bTermationProtection = myStack.getEnableTerminationProtection();
        logger.log("Description: " + description);
        logger.log("Protection:  " + bTermationProtection);
        String result = "Termination protection for " + stackName + " is " + bTermationProtection;
        logger.log(result);
        return result;
    }
}

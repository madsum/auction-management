package com.myorg;


import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.StackProps;

public class AwsJavaCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new ProductCmdStack(app, "ProductCmdStack", StackProps.builder()
                .stackName("ProductCmdStack")
                .build());

        new ProductCmdStack(app, "ProductQueryStack", StackProps.builder()
                .stackName("ProductQueryStack")
                .build());

        new ProductCmdStack(app, "SchedulerStack", StackProps.builder()
                .stackName("SchedulerStack")
                .build());

        new SignUpStack(app, "SignUpStack", StackProps.builder()
                .stackName("SignUpStack")
                .build());

        app.synth();
    }
}


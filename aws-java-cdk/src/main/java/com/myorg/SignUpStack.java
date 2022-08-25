package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcProps;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ClusterProps;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateServiceProps;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;

public class SignUpStack extends Stack {

  public SignUpStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    Vpc vpc = new Vpc(this, "SignUpVpc", VpcProps.builder()
      .maxAzs(2)
      .build());

    Cluster cluster = new Cluster(this, "SignUpCluster", ClusterProps.builder()
      .vpc(vpc)
      .build());

    new ApplicationLoadBalancedFargateService(this, "SignUpFargate", ApplicationLoadBalancedFargateServiceProps.builder()
      .cluster(cluster)
      .publicLoadBalancer(true)
      .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
        .image(ContainerImage.fromAsset("..\\auction.user.singup\\"))
        .containerPort(8085)
        .build())
      .desiredCount(2)
      .build());

  }
}

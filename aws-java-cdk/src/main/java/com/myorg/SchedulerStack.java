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

public class SchedulerStack extends Stack {

  public SchedulerStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    Vpc vpc = new Vpc(this, "SchedulerVpc", VpcProps.builder()
      .maxAzs(2)
      .build());

    Cluster cluster = new Cluster(this, "SchedulerCluster", ClusterProps.builder()
      .vpc(vpc)
      .build());

    new ApplicationLoadBalancedFargateService(this, "SchedulerFargate", ApplicationLoadBalancedFargateServiceProps.builder()
      .cluster(cluster)
      .publicLoadBalancer(true)
      .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
        .image(ContainerImage.fromAsset("..\\product.scheduler\\"))
        .containerPort(8086)
        .build())
      .desiredCount(2)
      .build());

  }
}

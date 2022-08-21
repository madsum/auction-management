import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as s3Deploy from 'aws-cdk-lib/aws-s3-deployment';
import * as cloudfront from 'aws-cdk-lib/aws-cloudfront';

export class AuctionStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // S3
    const bucket = new s3.Bucket(this, "AuctionReactAppBucket", {
      publicReadAccess: true,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      websiteIndexDocument: "index.html"
    });

  // Deployment
    const src = new s3Deploy.BucketDeployment(this, "DeployAuctionFrontEnd", {
      sources: [s3Deploy.Source.asset("../build")],
      destinationBucket: bucket
    });

   // CloudFront
    const cf = new cloudfront.CloudFrontWebDistribution(this, "CDKStaticDistribution", {
      originConfigs: [
        {
          s3OriginSource: {
            s3BucketSource: bucket
          },
          behaviors: [{isDefaultBehavior: true}]
        },
      ]
    });
  }
}

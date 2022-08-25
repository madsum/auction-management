#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { UserDatabaseCdkStack } from '../lib/user-database-cdk-stack';
import { ProductDatabaseCdkStack } from '../lib/product-database-cdk-stack';


const app = new cdk.App();
new UserDatabaseCdkStack(app, 'UserDatabaseCdkStack', {
  env: {
    region: 'us-east-1' // add region
  }
});

new ProductDatabaseCdkStack(app, 'ProductDatabaseCdkStack', {
  env: {
    region: 'us-east-1' // add region
  }
});
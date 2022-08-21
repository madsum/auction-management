#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { AuctionStack } from '../lib/aws-cdk-stack';

const app = new cdk.App();
new AuctionStack(app, 'AuctionStack', {
 env: {
     region: 'eu-west-1' // add region
   }
});
#!/bin/bash
set -o errexit

source ./scripts/set-env.sh

HOST_IP=`kubectl get ingress --namespace=$NAMESPACE | grep 'bills-http' | cut -d ' ' -f 10`

sudo echo "$HOST_IP $APP_DEV_HOST" >> /etc/hosts
#!/bin/bash
# workdir /

set -o errexit

source ./scripts/set-env.sh

minikube image load ${DEPLOYED_IMAGE}
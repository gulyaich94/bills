#!/bin/bash
# workdir /
set -o errexit

echo "======> Starting minikube... <======"
source ./scripts/start-minikube.sh
echo "======> Minikube is started <======\n"

echo "======> Starting upload image... <======"
source ./scripts/kube-upload-image.sh
echo "======> Image is uploaded <======\n"

echo "======> Starting deploy... <======"
source ./scripts/kube-deploy.sh
echo "======> Deploy is done <======\n"

echo "======> Open dashboard... <======"
source ./scripts/kube-dashboard.sh
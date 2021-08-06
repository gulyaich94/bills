#!/bin/bash
# workdir /
set -o errexit

echo "======> Building docker image... <======"
source ./scripts/docker-build-image.sh
echo -e "======> Docker image is built <======\n"

echo "======> Starting upload image... <======"
source ./scripts/kube-upload-image.sh
echo -e "======> Image is uploaded <======\n"

echo "======> Starting deploy... <======"
source ./scripts/kube-deploy.sh
echo -e "======> Deploy is done <======\n"

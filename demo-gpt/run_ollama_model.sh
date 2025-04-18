#!/bin/bash

# 定义 Ollama 容器名称
OLLAMA_CONTAINER_NAME="ollama"

# 定义 Ollama 数据卷名称
OLLAMA_VOLUME_NAME="ollama"

# 定义绑定的宿主机端口和容器端口
HOST_PORT="11434"
CONTAINER_PORT="11434"

# 定义要运行的模型
MODEL_NAME="qwen:0.5b"

# 检查 Ollama 容器是否已存在，如果存在则删除
if docker ps -a --format '{{.Names}}' | grep -qx "$OLLAMA_CONTAINER_NAME"; then
  echo "停止并删除已存在的 Ollama 容器: $OLLAMA_CONTAINER_NAME"
  docker stop "$OLLAMA_CONTAINER_NAME"
  docker rm "$OLLAMA_CONTAINER_NAME"
fi

# 检查 Ollama 数据卷是否存在，如果不存在则创建
if ! docker volume ls --format '{{.Name}}' | grep -qx "$OLLAMA_VOLUME_NAME"; then
  echo "创建 Ollama 数据卷: $OLLAMA_VOLUME_NAME"
  docker volume create "$OLLAMA_VOLUME_NAME"
fi

# 拉取 Ollama 镜像 (如果本地不存在)
if ! docker images -q ollama/ollama | grep -q "."; then
  echo "拉取 Ollama 镜像: ollama/ollama"
  docker pull ollama/ollama
else
  echo "Ollama 镜像已存在。"
fi

# 运行 Ollama 容器
echo "启动 Ollama 容器: $OLLAMA_CONTAINER_NAME"
docker run -d \
  -v "$OLLAMA_VOLUME_NAME":/root/.ollama \
  -p "$HOST_PORT":"$CONTAINER_PORT" \
  --name "$OLLAMA_CONTAINER_NAME" \
  ollama/ollama

# 等待 Ollama 服务启动 (可以根据实际情况调整等待时间)
echo "等待 Ollama 服务启动..."
sleep 5

# 运行指定的大模型
echo "运行大模型: $MODEL_NAME"
docker exec -it "$OLLAMA_CONTAINER_NAME" ollama run "$MODEL_NAME"

echo "Ollama 和 $MODEL_NAME 已启动并运行。"
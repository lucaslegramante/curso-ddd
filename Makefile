run:
	if [ "$$(uname -m)" == 'arm64' ]; then \
		docker-compose down && docker-compose up -d; \
	else \
		docker-compose down && docker-compose up -d; \
	fi
run:
	if [ "$$(uname -m)" == 'arm64' ]; then \
		docker-compose -f docker-compose.yml -f docker-compose-m1.yml down && docker-compose -f docker-compose.yml -f docker-compose-m1.yml up -d; \
	else \
		docker-compose down && docker-compose up -d; \
	fi

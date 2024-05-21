run:
	if [ "$$(uname -m)" == 'arm64' ]; then \
		docker-compose down && docker-compose up -d; \
	else \
		docker-compose down && docker-compose up -d; \
	fi

clean:
	./gradlew clean

build:clean
	./gradlew generateAvroJava build

test:
	./gradlew test

componentTest:
	./gradlew componentTest

up:
	docker-compose up -d

upm1:
	docker-compose -f docker-compose.yml -f docker-compose-m1.yml down && docker-compose -f docker-compose.yml -f docker-compose-m1.yml up -d

down:
	docker-compose down

detekt:
	./gradlew detekt

component-test: run
	SPRING_PROFILES_ACTIVE=test AWS_ACCESS_KEY_ID=foo AWS_SECRET_ACCESS_KEY=bar ./gradlew componentTest

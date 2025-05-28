build_dev:
	docker build -t srm_admin --build-arg ACTIVE_PROFILE=dev .
build_test:
	docker build -t srm_admin --build-arg ACTIVE_PROFILE=test .
build_prod:
	docker build -t srm_admin --build-arg ACTIVE_PROFILE=prod .
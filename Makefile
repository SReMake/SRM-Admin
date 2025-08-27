build_dev:
	docker build -t srm_admin_back_end --build-arg ACTIVE_PROFILE=dev .
build_test:
	docker build -t srm_admin_back_end --build-arg ACTIVE_PROFILE=test .
build_prod:
	docker build -t srm_admin_back_end --build-arg ACTIVE_PROFILE=prod .
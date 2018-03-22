# hydra-curriculum-service
Fields in the Curriculum beans are as follows:
* Curriculum
	* Integer curriculumId
	* String curriculumName

The controller can perform the following functions:
* CurriculumController
	* findOneCurriculum(@PathVariable Integer id), via a GET to /one/curriculum/{id}
	* findAllCurriculum(), via a GET to /all/curriculum
	* createCurriculum(@Valid @RequestBody Curriculum curriculum), via a POST to /curriculum/create
	* updateCurriculum(@Valid @RequestBody Curriculum curriculum), via a PUT to /curriculum/update
	* deleteCurriculum(@PathVariable int id), via a DELETE to /curriculum/delete/{id}

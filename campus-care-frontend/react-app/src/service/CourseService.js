import axios from "axios";

const CourseService = (function () {
  const _getAllCourses = async () => {
    const response = await axios.get("http://localhost:8080/api/v1/courses");
    if (response) {
      return response.data;
    }
  };

  const _getCourseById = async (id) => {
    const url = "http://localhost:8080/api/v1/courses/".concat(id);
    const response = await axios.get(url);
    if (response) {
      return response.data;
    }
  };

  const _getStudentList = async (courseCode) => {
    let url = "http://localhost:8080/api/v1/courses/".concat(courseCode);
    const response = await axios.get(url.concat("/studentList"));
    if (response) {
      return response.data;
    }
  };

  return {
    getCourseById: _getCourseById,
    getAllCourses: _getAllCourses,
    getStudentList: _getStudentList,
  };
})();

export default CourseService;

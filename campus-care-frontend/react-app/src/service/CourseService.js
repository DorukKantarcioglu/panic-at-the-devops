import axios from "axios";

const CourseService = (function () {

  const path = "http://localhost:8080/api/v1/courses"

  const _getAllCourses = async () => {
    const response = await axios.get(path);
    if (response) {
      return response.data;
    }
  };

  const _getCourseById = async (id) => {
    const url = path.concat("/").concat(id);
    const response = await axios.get(url);
    if (response) {
      return response.data;
    }
  };

  const _getStudentList = async (courseCode) => {
    let url = path.concat("/").concat(courseCode);
    const response = await axios.get(url.concat("/studentList"));
    if (response) {
      return response.data;
    }
  };

  const _addSeatingPlan=async (seatingPlanId, courseCode) => {
    console.log(seatingPlanId)
    debugger
    const response = await axios.patch(path.concat("/").concat(courseCode).concat("/seatingPlan"),{}, {
      headers: {
        seatingPlanId: seatingPlanId
      }
    })

    if (response) {
      return response.data;
    }
  }

  const _getSeatingPlan=async (courseCode) => {

    const response = await axios.get(path.concat("/").concat(courseCode).concat("/seatingPlan"));
    if (response) {
      return response.data;
    }
  }


  return {
    getCourseById: _getCourseById,
    getAllCourses: _getAllCourses,
    getStudentList: _getStudentList,
    getSeatingPlan: _getSeatingPlan,
    addSeatingPlan: _addSeatingPlan
  };
})();

export default CourseService;

import axios from "axios";
import {useEffect} from "react";

import LocalStorageService from "./LocalStorageService";

const CourseService = (function () {


  const path = "http://localhost:8080/api/v1/courses"
  const tokenHeader = {
    Authorization : "Bearer " + LocalStorageService.getToken()
  }

  const _getAllCourses = async () => {

    const response = await axios.get(path, {headers: {Authorization: 'Bearer ' + LocalStorageService.getToken()}});
    if (response) {
      return response.data;
    }
  };

  const _getCourseById = async (id) => {
    const url = path.concat("/").concat(id);
    const response = await axios.get(url,{headers:tokenHeader});
    if (response) {
      return response.data;
    }
  };

  const _getStudentList = async (courseCode) => {
    let url = path.concat("/").concat(courseCode);
    const response = await axios.get(url.concat("/studentList"),{headers:tokenHeader});
    if (response) {
      return response.data;
    }
  };

  const _addSeatingPlan=async (seatingPlanId, courseCode) => {
    const response = await axios.patch(path.concat("/").concat(courseCode).concat("/seatingPlan"),{}, {
      headers: {
        seatingPlanId: seatingPlanId,
        Authorization : "Bearer " + LocalStorageService.getToken()
      }
    })

    if (response) {
      return response.data;
    }
  }

  const _getSeatingPlan=async (courseCode) => {

    const response = await axios.get(path.concat("/").concat(courseCode).concat("/seatingPlan"),{headers:{
        Authorization : "Bearer " + LocalStorageService.getToken()
      }});
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

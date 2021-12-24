import axios from "axios";

const CovidInfoService = (function () {
  const _getNotAllowedStudents = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/students"
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotAllowedInstructors = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/instructors"
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotAllowedStaffs = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/staffs"
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotAllowedStudentsInCourse = async (courseCode) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/courses/".concat(courseCode)
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotAllowedStudentsInCafeteria = async (cafeteriaName) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/cafeterias/".concat(cafeteriaName)
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotAllowedStudentsInSmokingArea = async (smokingAreaName) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/covid/smokingAreas/".concat(smokingAreaName)
    );
    if (response) {
      return response.data;
    }
  };

  const _validateHesCode = async (hesCode, trIdNumber, eGovernmentPassword) => {
    const response = await axios.put(
      "http://localhost:8080/api/v1/covid",
      {},
      {
        headers: {
          hesCode: hesCode,
          tridNumber: trIdNumber,
          eGovernmentPassword: eGovernmentPassword,
        },
      }
    );
    if (response) {
      return response.data;
    }
  };

  const _validateHesCode2 = async (trIdNumber, eGovernmentPassword) => {
    const response = await axios.put(
      "http://localhost:8080/api/v1/covid",
      {},
      {
        headers: {
          tridNumber: trIdNumber,
          eGovernmentPassword: eGovernmentPassword,
        },
      }
    );
    if (response) {
      return response.data;
    }
  };

  return {
    getNotAllowedStudents: _getNotAllowedStudents,
    getNotAllowedInstructors: _getNotAllowedInstructors,
    getNotAllowedStaffs: _getNotAllowedStaffs,
    getNotAllowedStudentsInCourse: _getNotAllowedStudentsInCourse,
    getNotAllowedStudentsInCafeteria: _getNotAllowedStudentsInCafeteria,
    getNotAllowedStudentsInSmokingArea: _getNotAllowedStudentsInSmokingArea,
    validateHesCode: _validateHesCode,
    validateHesCode2: _validateHesCode2,
  };
})();

export default CovidInfoService;

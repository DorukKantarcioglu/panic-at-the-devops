import StudentService from "../../service/StudentService";
import StudentList from "../components/profileComponents/StudentList";
import { useEffect, useState } from "react";
import CourseService from "../../service/CourseService";

const CoursePage = () => {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    setData(await CourseService.getStudentList("cs315"));
  };

  useEffect(() => {
    fetchData().then();
  }, []);

  return (
    <div>
      <StudentList data={data} />
    </div>
  );
};

export default CoursePage;

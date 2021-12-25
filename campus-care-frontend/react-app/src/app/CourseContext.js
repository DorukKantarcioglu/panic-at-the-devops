import { createContext } from "react";
import CourseService from "../service/CourseService";

//Here, we set the initial fontSize as 16.
const list = CourseService.getAllCourses()
const courseContext = createContext(list);
export default courseContext;

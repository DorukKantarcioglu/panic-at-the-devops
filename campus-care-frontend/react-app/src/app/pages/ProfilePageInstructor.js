import InfoBox from "../components/profileComponents/InfoBox";
import { Container, Row } from "react-bootstrap";
import StudentList from "../components/profileComponents/StudentList";
import CoursePage from "./CoursePage";

class ProfilePageInstructor extends React.Component {
  constructor() {
    super();
    this.state = {
      name: "Elif",
      surname: "Cenesiz",
      id: 21902461,
      hesCode: "XXX",
    };
  }

  render() {
    return (
      <Container>
        <Row>
          <InfoBox />
        </Row>
        <Row>
          <CoursePage />
        </Row>
        <Row>Appointments (To-do)</Row>
      </Container>
    );
  }
}
export default ProfilePageInstructor;

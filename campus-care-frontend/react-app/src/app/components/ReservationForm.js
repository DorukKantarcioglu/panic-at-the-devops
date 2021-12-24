import React from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Container, Col, Row } from "react-bootstrap";
import SideBar from "./SideBar";

const ReservationForm = ({ create }) => {
  return (
    <Container>
      <Row>
        <Col sm={12}>
          <Form>
            <Form.Group>
              <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                Choose location
              </Form.Label>
              <Form.Select defaultValue="Choose...">
                <option>Main Sports Hall</option>
                <option>Dormitory Sports Hall</option>
                <option>Library ( Main Campus )</option>
                <option>Library ( East Campus )</option>
                <option>Diagnovir</option>
              </Form.Select>
            </Form.Group>

            <Form.Group>
              <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                Time Interval
              </Form.Label>
              <Form.Select defaultValue="Choose...">
                <option>10:30</option>
                <option>...</option>
              </Form.Select>
            </Form.Group>

            <Row className="mb-2">
              <Col>
                <Button variant="primary" type="submit">
                  Reserve
                </Button>
              </Col>
              <Col>
                <Button variant="primary" type="submit">
                  Cancel
                </Button>
              </Col>
            </Row>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default ReservationForm;

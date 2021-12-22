import { Form, Button, Card, App, Modal } from "react-bootstrap";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import React from "react";
import LocalStorageService from "../../../service/LocalStorageService";
import axios from "axios";
import Dashboard from "../../Dashboard";
export default function ForgotPasswordForm(props) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

import React from "react";
import { addExercise } from "./api";

const Modal = ({ setExercises, user }) => {
  const [speed, setSpeed] = React.useState(0);
  const [time, setTime] = React.useState(0);

  function handleClick() {
    addExercise({ speed, time }, user?.id)
      .then((data) => {
        setExercises((prev) => [...prev, data]);
      })
      .catch((err) => console.error(err));
  }

  return (
    <>
      <button
        type="button"
        className="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
        data-bs-whatever="@mdo"
      >
        Add Workout
      </button>
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                New Workout
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <form>
                <div className="mb-3">
                  <label htmlFor="recipient-name" className="col-form-label">
                    Speed:
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="recipient-name"
                    onChange={(e) => setSpeed(parseInt(e.target.value))}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="message-text" className="col-form-label">
                    Time:
                  </label>
                  <input
                    className="form-control"
                    id="message-text"
                    onChange={(e) => setTime(parseInt(e.target.value))}
                  />
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={handleClick}
              >
                Send message
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Modal;

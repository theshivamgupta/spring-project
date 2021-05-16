import React from "react";
import { addExercise, deleteExercise, getUser, signin, signup } from "./api";
import Modal from "./Modal";
import UpdateProfile from "./UpdateProfile";

function App() {
  const [username, setUsername] = React.useState("");
  const [height, setHeight] = React.useState(0);
  const [weight, setWeight] = React.useState(0);
  const [age, setAge] = React.useState(0);
  const [userFound, setUserFound] = React.useState(false);
  const [exercises, setExercises] = React.useState([]);
  const [user, setUser] = React.useState([]);
  const [error, setError] = React.useState(false);

  function onSubmit(alreadyReg = false, event) {
    // console.log(event);
    event.preventDefault();
    // event.stopImmediatePropagation();
    if (alreadyReg) {
      signin(username)
        .then((data) => {
          setExercises(data);
        })
        .catch((err) => {
          // event.stopImmediatePropagation();
          setError(true);
          console.log(err);
        });
      getUser(username)
        .then((data) => {
          setUser(data);
          setUserFound(true);
        })
        .catch((err) => {
          setError(true);
          console.error(err);
        });
    } else {
      console.log("register");
      signup({ username, height, weight, age })
        .then((data) => {
          console.log(data);
        })
        .catch((err) => {
          setError(true);
          console.error(err);
        });
    }
  }

  function handleDeleteExercise(id) {
    deleteExercise(id)
      .then((data) => {
        console.log(data);
        setExercises(exercises.filter((exercise) => exercise?.id !== id));
      })
      .catch((err) => {
        setError(true);
        console.error(err);
      });
  }

  const errorMessage = () => {
    return (
      <div className="row">
        <div className="col-md-6 offset-sm-3 text-left">
          <div
            className="alert alert-danger"
            style={{ display: error ? "block" : "none" }}
          >
            "Some error Occured"
          </div>
        </div>
      </div>
    );
  };

  if (error) {
    console.log("was here");
    return <div>{errorMessage()}</div>;
  }

  return (
    <div>
      {userFound && <Modal setExercises={setExercises} user={user} />}
      {userFound && <UpdateProfile />}
      <div className="row">
        <div className="col-md-6 offset-sm-3 text-left">
          <form>
            <div className="form-group p-2">
              <label className="text-light"></label>
              <input
                onChange={(e) => setUsername(e.target.value)}
                // value={email}
                className="form-control"
                type="text"
              />
              <input
                onChange={(e) => setHeight(parseInt(e.target.value))}
                // value={email}
                className="form-control"
                type="text"
                placeholder="Height(only for registration)"
              />
              <input
                onChange={(e) => setWeight(parseInt(e.target.value))}
                // value={email}
                className="form-control"
                type="text"
                placeholder="Weight(only for registration)"
              />
              <input
                onChange={(e) => setAge(parseInt(e.target.value))}
                // value={email}
                className="form-control"
                type="number"
                min="13"
                max="99"
                placeholder="Age(only for registration)"
              />
            </div>

            <button
              onClick={(e) => onSubmit(true, e)}
              className="btn btn-success btn-block mr-5"
            >
              Submit
            </button>
            <button
              onClick={(e) => onSubmit(false, e)}
              className="btn btn-danger btn-block mr-3"
            >
              Register
            </button>
          </form>
        </div>
      </div>
      {errorMessage()}
      <p className="h3 p-2">
        Total Calories burnt{" "}
        <span className="text-muted">{user?.totalCalories || 0}</span>
      </p>

      {exercises.length >= 0 &&
        exercises?.map((exercise) => (
          <div key={exercise?.id}>
            <div className="card p-2">
              <div className="card-header">{exercise?.runTime}</div>
              <div className="card-body">
                <h5 className="card-title">Speed {exercise?.speed}</h5>
                <h5 className="card-title">RunTime {exercise?.runTime}</h5>
                <p className="card-text">
                  Calories Burnt {exercise?.caloriesBurnt}
                </p>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDeleteExercise(exercise?.id)}
                >
                  Delete
                </button>
                <button className="btn btn-primary">Update</button>
              </div>
            </div>
          </div>
        ))}
    </div>
  );
}

export default App;

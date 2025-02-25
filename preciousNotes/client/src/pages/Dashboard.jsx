import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'
import GoalForm from '../components/GoalForm'
import GoalItem from '../components/GoalItem'
import Spinner from '../components/Spinner'
import { getGoals, reset } from '../features/goals/goalSlice'

function Dashboard() {
  const navigate = useNavigate()
  const dispatch = useDispatch()

  const { user } = useSelector((state) => state.auth)
  const { goals, isLoading, isError, message } = useSelector(
    (state) => state.goals
  )
  const [updateTrigger, setUpdateTrigger] = useState(false) // State to force refresh

  useEffect(() => {
    if (isError) {
      console.log(message)
    }

    if (!user) {
      navigate('/login')
    }

    dispatch(getGoals())

    return () => {
      dispatch(reset())
    }
  }, [user, navigate, isError, message, dispatch, updateTrigger])

  const handleRefresh = () => {
    setUpdateTrigger((prev) => !prev) // Toggle state to re-fetch goals
  }

  if (isLoading) {
    return <Spinner />
  }

  return (
    <>
      <section className='heading'>
        <h1>Welcome {user && user.user.name}</h1>
        <p>Notes App</p>
      </section>

      <GoalForm />
      <br />

      <section className='content'>
        {goals.length > 0 ? (
          <div className='goals'>
            {goals.map((goal) => (
              <GoalItem key={goal._id} goal={goal} onGoalDeleted={handleRefresh} />
            ))}
          </div>
        ) : (
          <h3>You have not taken any Notes yet</h3>
        )}
      </section>
    </>
  )
}

export default Dashboard

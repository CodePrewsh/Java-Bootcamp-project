import { useDispatch } from 'react-redux'
import { deleteGoal } from '../features/goals/goalSlice'

function GoalItem({ goal, onGoalDeleted }) {
  const dispatch = useDispatch()

  const handleDelete = async () => {
    const resultAction = await dispatch(deleteGoal(goal.id)) // Dispatch the delete action
    if (deleteGoal.fulfilled.match(resultAction)) {
      onGoalDeleted() // Notify the parent component of the deletion
    }
  }

  return (
    <div className='goal'>
      <div>{new Date(goal.createdAt).toLocaleString()}</div>
      <h2>{goal.title}</h2>
      <p>{goal.content}</p>
      <button onClick={handleDelete} className='close'>
        X
      </button>
    </div>
  )
}

export default GoalItem

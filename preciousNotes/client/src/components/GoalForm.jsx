import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { createGoal, getGoals } from '../features/goals/goalSlice'

function GoalForm() {
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')
  const dispatch = useDispatch()

  const onSubmit = async (e) => {
    e.preventDefault()

    // Dispatch the createGoal action
    const resultAction = await dispatch(createGoal({ title, content }))

    // Check if the creation was successful
    if (createGoal.fulfilled.match(resultAction)) {
      // Refresh the goals list
      dispatch(getGoals())
    }

    setTitle('') // Clear the input field
    setContent('') // Clear the input field
  }

  return (
    <section className='form'>
      <form onSubmit={onSubmit}>
        <div className='form-group'>
          <label htmlFor='title'>Title</label>
          <input
            type='text'
            name='title'
            id='title'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>

        <div className='form-group'>
          <label htmlFor='content'>Content</label>
          <textarea
            type='text'
            name='content'
            id='content'
            value={content}
            onChange={(e) => setContent(e.target.value)}
          > </textarea>
        </div>

        <div className='form-group'>
          <button className='btn btn-block' type='submit'>
            Add Notes
          </button>
        </div>
      </form>
    </section>
  )
}

export default GoalForm

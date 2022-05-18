INSERT INTO foo (id, name, description) VALUES 
  (1, 'one', 'first foo'),
  (2, 'two', 'second foo'),
  (3, 'three', 'third foo') ON CONFLICT DO NOTHING;
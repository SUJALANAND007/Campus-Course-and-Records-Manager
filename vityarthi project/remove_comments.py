import os
import re

def remove_comments_from_file(file_path):
    # Read the file content
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()
    
    # Remove single-line comments (// ...)
    content = re.sub(r'//.*', '', content)
    
    # Remove multi-line comments (/* ... */)
    content = re.sub(r'/\*.*?\*/', '', content, flags=re.DOTALL)
    
    # Remove empty lines and extra whitespace
    lines = [line.strip() for line in content.splitlines() if line.strip()]
    content = '\n'.join(lines)
    
    # Write the cleaned content back to the file
    with open(file_path, 'w', encoding='utf-8') as file:
        file.write(content)

def process_directory(directory):
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                try:
                    remove_comments_from_file(file_path)
                    print(f"Processed: {file_path}")
                except Exception as e:
                    print(f"Error processing {file_path}: {e}")

if __name__ == "__main__":
    src_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'src')
    process_directory(src_dir)
    print("Comment removal process completed.")
